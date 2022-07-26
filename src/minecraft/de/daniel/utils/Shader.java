package de.daniel.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.GL20;

public class Shader {

    private static final String VERTEX_SHADER = "#version 130\n" +
            "\n" +
            "void main() {\n" +
            " gl_TexCoord[0] = gl_MultiTexCoord0;\n" +
            " gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n" +
            "}";


    public static String V  = "//Set to 2.0 for AA\n" +
            "#define AA 1.0\n" +
            "\n" +
            "#define STEPS 80.0\n" +
            "#define MDIST 35.0\n" +
            "#define pi 3.1415926535\n" +
            "#define rot(a) mat2(cos(a),sin(a),-sin(a),cos(a))\n" +
            "#define sat(a) clamp(a,0.0,1.0)\n" +
            "\n" +
            "#define ITERS_TRACE 9\n" +
            "#define ITERS_NORM 20\n" +
            "\n" +
            "#define HOR_SCALE 1.1\n" +
            "#define OCC_SPEED 1.4\n" +
            "#define DX_DET 0.65\n" +
            "\n" +
            "#define FREQ 0.6\n" +
            "#define HEIGHT_DIV 2.5\n" +
            "#define WEIGHT_SCL 0.8\n" +
            "#define FREQ_SCL 1.2\n" +
            "#define TIME_SCL 1.095\n" +
            "#define WAV_ROT 1.21\n" +
            "#define DRAG 0.6\n" +
            "#define SCRL_SPEED 1.5\n" +
            "vec2 scrollDir = vec2(1,1);\n" +
            "\n" +
            "//Built with some ideas from\n" +
            "//https://www.shadertoy.com/view/wldBRf\n" +
            "//https://www.shadertoy.com/view/ssG3Wt\n" +
            "//https://www.shadertoy.com/view/4dBcRD\n" +
            "//https://www.shadertoy.com/view/Xdlczl\n" +
            "vec2 wavedx(vec2 wavPos, int iters, float t){\n" +
            "    vec2 dx = vec2(0);\n" +
            "    vec2 wavDir = vec2(1,0);\n" +
            "    float wavWeight = 1.0; \n" +
            "    wavPos+= t*SCRL_SPEED*scrollDir;\n" +
            "    wavPos*= HOR_SCALE;\n" +
            "    float wavFreq = FREQ;\n" +
            "    float wavTime = OCC_SPEED*t;\n" +
            "    for(int i=0;i<iters;i++){\n" +
            "        wavDir*=rot(WAV_ROT);\n" +
            "        float x = dot(wavDir,wavPos)*wavFreq+wavTime; \n" +
            "        float result = exp(sin(x)-1.)*cos(x)*wavWeight; \n" +
            "        dx+= result*wavDir/pow(wavWeight,DX_DET); \n" +
            "        wavFreq*= FREQ_SCL; \n" +
            "        wavTime*= TIME_SCL;\n" +
            "        wavPos-= wavDir*result*DRAG; \n" +
            "        wavWeight*= WEIGHT_SCL;\n" +
            "    }\n" +
            "    float wavSum = -(pow(WEIGHT_SCL,float(iters))-1.)*HEIGHT_DIV; \n" +
            "    return dx/pow(wavSum,1.-DX_DET);\n" +
            "}\n" +
            "\n" +
            "float wave(vec2 wavPos, int iters, float t){\n" +
            "    float wav = 0.0;\n" +
            "    vec2 wavDir = vec2(1,0);\n" +
            "    float wavWeight = 1.0;\n" +
            "    wavPos+= t*SCRL_SPEED*scrollDir;\n" +
            "    wavPos*= HOR_SCALE; \n" +
            "    float wavFreq = FREQ;\n" +
            "    float wavTime = OCC_SPEED*t;\n" +
            "    for(int i=0;i<iters;i++){\n" +
            "        wavDir*=rot(WAV_ROT);\n" +
            "        float x = dot(wavDir,wavPos)*wavFreq+wavTime;\n" +
            "        float wave = exp(sin(x)-1.0)*wavWeight;\n" +
            "        wav+= wave;\n" +
            "        wavFreq*= FREQ_SCL;\n" +
            "        wavTime*= TIME_SCL;\n" +
            "        wavPos-= wavDir*wave*DRAG*cos(x);\n" +
            "        wavWeight*= WEIGHT_SCL;\n" +
            "    }\n" +
            "    float wavSum = -(pow(WEIGHT_SCL,float(iters))-1.)*HEIGHT_DIV; \n" +
            "    return wav/wavSum;\n" +
            "}\n" +
            "\n" +
            "vec3 norm(vec3 p){\n" +
            "    vec2 wav = -wavedx(p.xz, ITERS_NORM, iTime);\n" +
            "    return normalize(vec3(wav.x,1.0,wav.y));\n" +
            "}\n" +
            "\n" +
            "float map(vec3 p){\n" +
            "    float a = 0.;\n" +
            "    int steps = ITERS_TRACE;\n" +
            "    p.y-= wave(p.xz,steps,iTime);\n" +
            "    a = p.y;\n" +
            "    return a;\n" +
            "}\n" +
            "\n" +
            "vec3 pal(float t, vec3 a, vec3 b, vec3 c, vec3 d){\n" +
            "    return a+b*cos(2.0*pi*(c*t+d));\n" +
            "}\n" +
            "vec3 spc(float n,float bright){\n" +
            "    return pal(n,vec3(bright),vec3(0.5),vec3(1.0),vec3(0.0,0.33,0.67));\n" +
            "}\n" +
            "vec2 sunrot = vec2(-0.3,-0.25);\n" +
            "\n" +
            "//Change the color of the scene here, it better withs some colors than others\n" +
            "float spec = 0.13;\n" +
            "\n" +
            "vec3 sky(vec3 rd){\n" +
            "    float px = 1.5/min(iResolution.x,iResolution.y);\n" +
            "    vec3 rdo = rd;\n" +
            "    float rad = 0.075;\n" +
            "    vec3 col = vec3(0);\n" +
            "\n" +
            "    //Sun\n" +
            "    rd.yz*=rot(sunrot.y);\n" +
            "    rd.xz*=rot(sunrot.x);\n" +
            "    float sFade = 2.5/min(iResolution.x,iResolution.y);\n" +
            "    float zFade = rd.z*0.5+0.5;\n" +
            "    \n" +
            "    vec3 sc = spc(spec-0.1,0.6)*0.85;\n" +
            "    float a = length(rd.xy);\n" +
            "    vec3 sun=smoothstep(a-px-sFade,a+px+sFade,rad)*sc*zFade*2.;\n" +
            "    col+=sun;\n" +
            "    col+=rad/(rad+pow(a,1.7))*sc*zFade;\n" +
            "    col=col+mix(col,spc(spec+0.1,0.8),sat(1.0-length(col)))*0.2;\n" +
            "    \n" +
            "    //This code provides the implication of clouds :)\n" +
            "    float e = 0.;\n" +
            "    vec3 p = rdo;\n" +
            "    p.xz*=0.4;\n" +
            "    p.x+=iTime*0.007;\n" +
            "    for(float s=200.;s>10.;s*=0.8){\n" +
            "        p.xz*=rot(s);\n" +
            "        p+=s;\n" +
            "        e+=abs(dot(sin(p*s+iTime*0.02)/s,vec3(1.65)));\n" +
            "    }\n" +
            "    e*=smoothstep(0.5,0.4,e-0.095);\n" +
            "    \n" +
            "    col += (e)*smoothstep(-0.02,0.3,rdo.y)*0.8*(1.0-sun*3.75)*mix(sc,vec3(1),0.4);\n" +
            "    \n" +
            "    return (col);\n" +
            "}\n" +
            "void render( out vec4 fragColor, in vec2 fragCoord ){\n" +
            "    vec2 uv = (fragCoord-0.5*iResolution.xy)/min(iResolution.y,iResolution.x);\n" +
            "    vec3 col = vec3(0);\n" +
            "    vec3 ro = vec3(0,2.25,-3)*1.1;\n" +
            "    bool click = iMouse.z>0.;\n" +
            "    if(click){\n" +
            "    ro.yz*=rot(2.0*min(iMouse.y/iResolution.y-0.5,0.15));\n" +
            "    ro.zx*=rot(-7.0*(iMouse.x/iResolution.x-0.5));\n" +
            "    }\n" +
            "    vec3 lk = vec3(0,2,0);\n" +
            "    vec3 f = normalize(lk-ro);\n" +
            "    vec3 r = normalize(cross(vec3(0,1,0),f));\n" +
            "    vec3 rd = normalize(f*(0.9)+uv.x*r+uv.y*cross(f,r));\n" +
            "\n" +
            "    float dO = 0.;\n" +
            "    bool hit = false;\n" +
            "    float d = 0.;\n" +
            "    vec3 p = ro;\n" +
            "\n" +
            "    float tPln = -(ro.y-1.86)/rd.y;\n" +
            "    if(tPln>0.||click){\n" +
            "        if(!click)dO+=tPln;\n" +
            "        for(float i = 0.; i<STEPS; i++){\n" +
            "            p = ro+rd*dO;d = map(p);dO+=d;\n" +
            "            if(abs(d)<0.005||i>STEPS-2.0){\n" +
            "                hit = true;\n" +
            "                break;\n" +
            "            }\n" +
            "            if(dO>MDIST){\n" +
            "                dO = MDIST;\n" +
            "                break;\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "    vec3 skyrd = sky(rd);\n" +
            "    if(hit){\n" +
            "    vec3 n = norm(p);\n" +
            "    vec3 rfl = reflect(rd,n);\n" +
            "    rfl.y = abs(rfl.y);\n" +
            "    vec3 rf = refract(rd,n,1./1.33); \n" +
            "    vec3 sd = normalize(vec3(0,0.3,-1.0));\n" +
            "    float fres = clamp((pow(1. - max(0.0, dot(-n, rd)), 5.0)),0.0,1.0);\n" +
            "    \n" +
            "    vec3 sunDir = vec3(0,0.15,1.0);\n" +
            "    sunDir.xz*=rot(-sunrot.x);\n" +
            "    col += sky(rfl)*fres*0.9;\n" +
            "    float subRefract =pow(max(0.0, dot(rf,sunDir)),35.0);\n" +
            "    //This effect is exaggerated much more than is realistic because I like it :) \n" +
            "    col += pow(spc(spec-0.1,0.5),vec3(2.2))*subRefract*2.5;\n" +
            "    vec3 rd2 = rd;\n" +
            "    rd2.xz*=rot(sunrot.x);\n" +
            "    vec3 waterCol = min(sat(spc(spec-0.1,0.4))*0.05*pow(min(p.y+0.5,1.8),4.0)*length(skyrd)*(rd2.z*0.3+0.7),1.0);\n" +
            "   \n" +
            "    waterCol = sat(spc(spec-0.1,0.4))*(0.4*pow(min(p.y*0.7+0.9,1.8),4.)*length(skyrd)*(rd2.z*0.15+0.85));\n" +
            "    \n" +
            "    col += waterCol*0.17;\n" +
            "    col = mix(col,skyrd,dO/MDIST);\n" +
            "    }\n" +
            "    else{\n" +
            "        col += skyrd;\n" +
            "    }\n" +
            "    col = sat(col);\n" +
            "    col = pow(col,vec3(0.87));\n" +
            "    \n" +
            "    col *=1.0-0.8*pow(length(uv*vec2(0.8,1.)),2.7);\n" +
            "    fragColor = vec4(col,1.0);\n" +
            "    \n" +
            "}\n" +
            "\n" +
            "#define ZERO min(0.0,iTime)\n" +
            "void mainImage( out vec4 fragColor, in vec2 fragCoord )\n" +
            "{\n" +
            "    float px = 1.0/AA;\n" +
            "    vec4 col = vec4(0);\n" +
            "    \n" +
            "    if(AA==1.0) {render(col,fragCoord); fragColor = col; return;}\n" +
            "    \n" +
            "    for(float i = ZERO; i <AA; i++){\n" +
            "        for(float j = ZERO; j <AA; j++){\n" +
            "            vec4 col2;\n" +
            "            vec2 coord = vec2(fragCoord.x+px*i,fragCoord.y+px*j);\n" +
            "            render(col2,coord);\n" +
            "            col.rgb+=col2.rgb;\n" +
            "        }\n" +
            "    }\n" +
            "    col/=AA*AA;\n" +
            "    fragColor = vec4(col);\n" +
            "}";
    private Minecraft mc = Minecraft.getMinecraft();
    private int program;
    private long startTime;

    /**
     * @param fragment The fragment shader code (not the resource location)
     */
    public Shader(String fragment) {
        program = GL20.glCreateProgram();
        startTime = System.currentTimeMillis();
        initShader(fragment);
    }

    private void initShader(String frag) {
        int vertex = GL20.glCreateShader(GL20.GL_VERTEX_SHADER), fragment = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(vertex, VERTEX_SHADER);
        GL20.  glShaderSource(fragment, frag);
        GL20.glValidateProgram(program);
        GL20. glCompileShader(vertex);
        GL20.glCompileShader(fragment);
        GL20.glAttachShader(program, vertex);
        GL20.glAttachShader(program, fragment);
        GL20.glLinkProgram(program);
    }

    public void renderFirst() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL20.glUseProgram(program);
        addDefaultUniforms();
    }

    public void renderSecond() {
        GL11.glEnable(GL11.GL_BLEND);
        GL11. glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        ScaledResolution sr = new ScaledResolution();
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2d(0, 1);
        GL11.glVertex2d(0, 0);
        GL11.glTexCoord2d(0, 0);
        GL11. glVertex2d(0, sr.getScaledHeight());
        GL11.glTexCoord2d(1, 0);
        GL11.glVertex2d(sr.getScaledWidth(), sr.getScaledHeight());
        GL11.glTexCoord2d(1, 1);
        GL11. glVertex2d(sr.getScaledWidth(), 0);
        GL11.glEnd();
        GL20.glUseProgram(0);
    }

    public void bind() {
        GL20.glUseProgram(program);
    }

    public int getProgram() {
        return program;
    }
    
    public Shader uniform1i(String loc, int i) {
        GL20.glUniform1i(GL20.glGetUniformLocation(program, loc), i);
        return this;
    }

    
    public Shader uniform2i(String loc, int i, int i1) {
        GL20.glUniform2i(GL20.glGetUniformLocation(program, loc), i, i1);
        return this;
    }

    
    public Shader uniform3i(String loc, int i, int i1, int i2) {
        GL20. glUniform3i(GL20.glGetUniformLocation(program, loc), i, i1, i2);
        return this;
    }

    
    public Shader uniform4i(String loc, int i, int i1, int i2, int i3) {
        GL20. glUniform4i(GL20.glGetUniformLocation(program, loc), i, i1, i2, i3);
        return this;
    }

    
    public Shader uniform1f(String loc, float f) {
        GL20. glUniform1f(GL20.glGetUniformLocation(program, loc), f);
        return this;
    }

    
    public Shader uniform2f(String loc, float f, float f1) {
        GL20.glUniform2f(GL20.glGetUniformLocation(program, loc), f, f1);
        return this;
    }

    
    public Shader uniform3f(String loc, float f, float f1, float f2) {
        GL20. glUniform3f(GL20.glGetUniformLocation(program, loc), f, f1, f2);
        return this;
    }

    
    public Shader uniform4f(String loc, float f, float f1, float f2, float f3) {
        GL20. glUniform4f(GL20.glGetUniformLocation(program, loc), f, f1, f2, f3);
        return this;
    }

    
    public Shader uniform1b(String loc, boolean b) {
        GL20.   glUniform1i(GL20.glGetUniformLocation(program, loc), b ? 1 : 0);
        return this;
    }

    public void addDefaultUniforms() {
        GL20.glUniform2f(GL20.glGetUniformLocation(program, "resolution"), mc.displayWidth, mc.displayHeight);
        float time = (System.currentTimeMillis() - this.startTime) / 1000f;
        GL20.glUniform1f(GL20.glGetUniformLocation(program, "time"), time);
    }
}