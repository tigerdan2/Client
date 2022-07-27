package de.daniel.gui;

import de.daniel.utils.Shader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

public class GuiHelper {


    public static double sigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }

    public static void drawBorderedRect(float left, float top, float right, float bottom, int borderColor, int color) {
        Gui.drawRect(left, top, right, bottom, color);
        Gui.drawRect(left, top, left-1, bottom, borderColor);
        Gui.drawRect(left, top, right, top-1, borderColor);
        Gui.drawRect(left, bottom, right, bottom+1, borderColor);
        Gui.drawRect(right, top, right+1, bottom, borderColor);
    }

    private static final String VERTEX_SHADER = "#version 120 \nvoid main() { \ngl_TexCoord[0] = gl_MultiTexCoord0; \ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex; \n}";
    private static final String FRAGMENT_SHADER ="// created by nardpw\n" +
            "\n" +
            "precision highp float;\n" +
            "\n" +
            "uniform float time;\n" +
            "uniform vec2 resolution;\n" +
            "\n" +
            "void main(void) {\n" +
            "    vec2 position = (gl_FragCoord.xy / resolution.xy) + vec2(0.12, 0.2);\n" +
            "\n" +
            "    float progress = (position.x * (sin(time / 1.0) + 1.0) / 1.0 + position.y * (cos(time / 2.0) + 2.0) / 3.5) * 2.0 + (cos(time) * 4.0 + 1.0) / 4.0;\n" +
            "\n" +
            "    progress /= sqrt(position.x * position.y);\n" +
            "\n" +
            "    progress = sin(progress) + 1.0;\n" +
            "    progress *= 1.2;\n" +
            "\n" +
            "    vec3 col1 = vec3(1.0, 0.733333333333333, 0.733333333333333);\n" +
            "    vec3 col2 = vec3(0.662745098039216, 0.945098039215686, 0.874509803921569);\n" +
            "    vec3 col3 = vec3(0.988235294117647, 0.647058823529412, 0.945098039215686);\n" +
            "    vec3 col4 = vec3(0.709803921568627, 1.0, 1.0);\n" +
            "\n" +
            "    vec3 a = col1 + (col2 - col1) * progress;\n" +
            "    vec3 b = a + (col3 - a) * progress / 2.0;\n" +
            "    vec3 c = b + (col4 - b) * progress / 4.0;\n" +
            "\n" +
            "    gl_FragColor = vec4(c, 1.0);\n" +
            "}";

    public static Shader shaderUtil = new Shader(FRAGMENT_SHADER);
    public static void drawDefaultBackGround(float mouseX,float mouseY){

        GL11.glPushMatrix();
   //     GL11.glColor3f(1,1,1);

        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        shaderUtil.renderFirst();
        shaderUtil.renderSecond();

        GL11.glPopMatrix();
    }
    public static void drawSidewaysGradientRect(double x, double y, double x2, double y2, int col1, int col2) {
        float f = (float)(col1 >> 24 & 255) / 255.0f;
        float f1 = (float)(col1 >> 16 & 255) / 255.0f;
        float f2 = (float)(col1 >> 8 & 255) / 255.0f;
        float f3 = (float)(col1 & 255) / 255.0f;
        float f4 = (float)(col2 >> 24 & 255) / 255.0f;
        float f5 = (float)(col2 >> 16 & 255) / 255.0f;
        float f6 = (float)(col2 >> 8 & 255) / 255.0f;
        float f7 = (float)(col2 & 255) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static void drawBorderedCircle(double x, double y, float radius, int outsideC, int insideC) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        float scale = 0.1f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        x = ((float)x * (1.0f / scale));
        y = ((float)y * (1.0f / scale));
        GuiHelper.drawCircle(x, y, radius *= 1.0f / scale, insideC);
        GuiHelper.drawUnfilledCircle(x, y, radius, 1.0f, outsideC);
        GL11.glScalef((float)(1.0f / scale), (float)(1.0f / scale), (float)(1.0f / scale));
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }



    public static void drawUnfilledCircle(double x, double y, float radius, float lineWidth, int color) {
        float alpha = (float)(color >> 24 & 255) / 255.0f;
        float red = (float)(color >> 16 & 255) / 255.0f;
        float green = (float)(color >> 8 & 255) / 255.0f;
        float blue = (float)(color & 255) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
        GL11.glLineWidth((float)lineWidth);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)2);
        int i = 0;
        while (i <= 360) {
            GL11.glVertex2d((double)((double)x + Math.sin((double)i * 3.141526 / 180.0) * (double)radius), (double)((double)y + Math.cos((double)i * 3.141526 / 180.0) * (double)radius));
            ++i;
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
    }

    public static void drawCircle(double x, double y, float radius, int color) {
        float alpha = (float)(color >> 24 & 255) / 255.0f;
        float red = (float)(color >> 16 & 255) / 255.0f;
        float green = (float)(color >> 8 & 255) / 255.0f;
        float blue = (float)(color & 255) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
        GL11.glBegin((int)9);
        int i = 0;
        while (i <= 360) {
            GL11.glVertex2d((double)((double)x + Math.sin((double)i * 3.141526 / 180.0) * (double)radius), (double)((double)y + Math.cos((double)i * 3.141526 / 180.0) * (double)radius));
            ++i;
        }
        GL11.glEnd();
    }

}
