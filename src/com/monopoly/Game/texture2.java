
/*
package com.monopoly.Game;


import java.applet.*;
import java.awt.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.lang.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.applet.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;

//1.����texture2�࣬��չApplet
public class texture2 extends Applet {
    //2.����createSceneGraph�������ķ�������һ��BranchGroup
    private BranchGroup createSceneGraph(){
        BranchGroup objroot = new BranchGroup();
//3.��������Point3f���͵��ı��εĶ������飬���ڴ���6���ı�����
        Point3f[] p1 = new Point3f[4];
        p1[0] = new Point3f(-.5f, .5f, 0.5f);
        p1[1] = new Point3f(-.5f, -.5f, 0.5f);
        p1[2] = new Point3f(.5f, -.5f, 0.5f);
        p1[3] = new Point3f(.5f, .5f, 0.5f);

        Point3f[] p2 = new Point3f[4];
        p2[0] = new Point3f(-.5f, .5f, -0.5f);
        p2[1] = new Point3f(-.5f, -.5f, -0.5f);
        p2[2] = new Point3f(-.5f, -.5f, 0.5f);
        p2[3] = new Point3f(-.5f, .5f, 0.5f);

        Point3f[] p3 = new Point3f[4];
        p3[0] = new Point3f(.5f, .5f, -0.5f);
        p3[1] = new Point3f(.5f, -.5f, -0.5f);
        p3[2] = new Point3f(-.5f, -.5f, -0.5f);
        p3[3] = new Point3f(-.5f, .5f, -0.5f);

        Point3f[] p4 = new Point3f[4];
        p4[0] = new Point3f(.5f, .5f, 0.5f);
        p4[1] = new Point3f(.5f, -.5f, 0.5f);
        p4[2] = new Point3f(.5f, -.5f, -0.5f);
        p4[3] = new Point3f(.5f, .5f, -0.5f);

        Point3f[] p5 = new Point3f[4];
        p5[0] = new Point3f(-.5f, .5f, 0.5f);
        p5[1] = new Point3f(.5f, .5f, 0.5f);
        p5[2] = new Point3f(.5f, .5f, -0.5f);
        p5[3] = new Point3f(-.5f, .5f, -0.5f);

        Point3f[] p6 = new Point3f[4];
        p6[0] = new Point3f(-.5f, -.5f, -0.5f);
        p6[1] = new Point3f(.5f, -.5f, -0.5f);
        p6[2] = new Point3f(.5f, -.5f, 0.5f);
        p6[3] = new Point3f(-.5f, -.5f, 0.5f);

//4.����ÿһ���ı����棬����shapeMaker(java.awt.Component observer,
//	String filename,Point3f[] p),��һ������ͼ��۲��ߣ��ڶ����������������ͼ���ļ���
//	�����������Ƕ�������꣬���ؽ���� Shape3D ���͵ļ��϶���
        Shape3D shape1 = shapeMaker(this,"C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images1.png",p1);
        Shape3D shape2 = shapeMaker(this,"C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images2.png",p2);
        Shape3D shape3 = shapeMaker(this,"C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images3.png",p3);
        Shape3D shape4 = shapeMaker(this,"C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images4.png",p4);
        Shape3D shape5 = shapeMaker(this,"C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images5.png",p5);
        Shape3D shape6 = shapeMaker(this,"C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images6.png",p6);

//5.��������ϵ trans, ���ı�������ӵ�����ϵ��
        Transform3D t3d = new Transform3D();
        TransformGroup trans = new TransformGroup();
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        trans.addChild(shape1);
        trans.addChild(shape2);
        trans.addChild(shape3);
        trans.addChild(shape4);
        trans.addChild(shape5);
        trans.addChild(shape6);
        objroot.addChild(trans);

//6.���������ת�任����ϵ
        MouseRotate behavior = new MouseRotate();
        behavior.setTransformGroup(trans);
        behavior.setSchedulingBounds(new BoundingSphere());
        objroot.addChild(behavior);
        objroot.compile();
        return objroot;

    }
    //7.���� shapeMaker �������÷������� Shape3D ����
    public Shape3D shapeMaker(java.awt.Component observer,
                              String filename, Point3f[] p){
        TextureLoader loader = new TextureLoader(filename,observer);
        ImageComponent2D myImage = loader.getImage();
        Texture myTex = loader.getTexture();
        myTex.setImage( 0, myImage );
        Appearance appear = new Appearance();
        appear.setTexture( myTex);

//8.�����ı����� plane
        QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES|
                GeometryArray.TEXTURE_COORDINATE_2);
        plane.setCoordinates(0, p);
//9.Ϊ�ı������ÿһ����������������
        TexCoord2f texCoords = new TexCoord2f();
        texCoords.set(0.0f, 1.0f);
        plane.setTextureCoordinate(0,0,texCoords);
        texCoords.set(0.0f, 0.0f);
        plane.setTextureCoordinate(0,1,texCoords);
        texCoords.set(1.0f, 0.0f);
        plane.setTextureCoordinate(0,2,texCoords);
        texCoords.set(1.0f, 1.0f);
        plane.setTextureCoordinate(0,3,texCoords);

//10.Ϊ�ı������ÿһ����������������
        Shape3D shape = new Shape3D(plane,appear);
        return shape;

    }
    //11.������ά����ͼ
    public texture2(){
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c = new Canvas3D(config);
        add("Center",c);
        BranchGroup scene = createSceneGraph();
        SimpleUniverse u = new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(scene);
    }

    public static void main(String[] args){
        new MainFrame(new texture2(),200,200);
    }
}
*/