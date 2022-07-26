package de.daniel.utils;

import de.Client.Main.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import optifine.Config;
import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import shadersmod.client.Shaders;
import shadersmod.client.ShadersTex;
import sun.util.calendar.BaseCalendar;

import java.sql.Date;
import java.util.Random;

public class ShaderUtil {

    private int program;

    /*
        @author Stephen Jones | Modified and improved by DiZe/Jan(https://www.youtube.com/channel/UCgurC9Q9MeOBxcI-7LdCB7g)
     */

    /*
        @params FragmentShader and Vertexshader of the shader that you want to use
     */
    public ShaderUtil(String FRAGMENTSHADER, String VERTEXSHADER) {
        int vertexShader = 0, fragmentShader = 0;

        try {
            vertexShader = createShader(VERTEXSHADER, ARBVertexShader.GL_VERTEX_SHADER_ARB);      //Compile vertexshader
            fragmentShader = createShader(FRAGMENTSHADER, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);  //Compiler fragmentshader
        } catch (Exception exc) {
            exc.printStackTrace();
            return;
        } finally {  //Even if theres an exception, this code will be executed
            if (vertexShader == 0 || fragmentShader == 0)return;
        }

        program = ARBShaderObjects.glCreateProgramObjectARB();

        if (program == 0)return;

         /*
        * if the vertex and fragment shaders setup sucessfully,
        * attach them to the shader program, link the sahder program
        * (into the GL context I suppose), and validate
        */

        ARBShaderObjects.glAttachObjectARB(program, vertexShader);
        ARBShaderObjects.glAttachObjectARB(program, fragmentShader);

        ARBShaderObjects.glLinkProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(program));
            return;
        }

        ARBShaderObjects.glValidateProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(program));
            return;
        }
    }

    public void draw() {


        ScaledResolution sr = new ScaledResolution();



        GL11.glPushMatrix();
        //Set uniforms
        double time = System.currentTimeMillis()*0.0005%800-400;
        GL20.glUseProgram(program);
        GL20.glUniform2f(GL20.glGetUniformLocation(program, "resolution"), (float) sr.getScaledWidth()*sr.getScaleFactor(), (float) sr.getScaledHeight()*sr.getScaleFactor()); //So setzt man ne uniform
        GL20.glUniform1f(GL20.glGetUniformLocation(program, "time"), (float) time);



        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(-1.0f, -1.0f);
        GL11.glVertex2f(1.0f, -1.0f);
        GL11.glVertex2f(1.0f, 1.0f);
        GL11.glVertex2f(-1.0f, 1.0f);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        GL20.glUseProgram(0); //"Unbinde" Shader

        GL11.glPopMatrix();
    }

    private int createShader(String shaderSource, int shaderType) throws Exception {
        int shader = 0;
        try {
            shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);

            if(shader == 0)return 0;

            ARBShaderObjects.glShaderSourceARB(shader, shaderSource);
            ARBShaderObjects.glCompileShaderARB(shader);

            if(ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)throw new RuntimeException("Error creating shader: " + getLogInfo(shader));

            return shader;
        } catch (Exception exc) {
            ARBShaderObjects.glDeleteObjectARB(shader);
            throw exc;

        }
    }

    private String getLogInfo(int obj) {
        return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }
}
