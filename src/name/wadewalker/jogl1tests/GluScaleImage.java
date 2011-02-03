package name.wadewalker.jogl1tests;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.ByteBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import org.junit.Test;

public class GluScaleImage implements GLEventListener {

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        int widthin = 559;
        int heightin = 425;
        
        int widthout = 1024;
        int heightout = 512;
        
        int textureInLength = widthin * heightin * 4;
        int textureOutLength = widthout * heightout * 4;
        
        byte[] datain = new byte[textureInLength];
        byte[] dataout = new byte[textureOutLength];
        
        ByteBuffer bufferIn  = ByteBuffer.wrap(datain);
        ByteBuffer bufferOut = ByteBuffer.wrap(dataout);      
        GLU glu = new GLU();
        glu.gluScaleImage( GL.GL_RGBA,
                           widthin, heightin, GL.GL_UNSIGNED_BYTE, bufferIn,
                           widthout, heightout, GL.GL_UNSIGNED_BYTE, bufferOut );
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    @Test
    public void test01() {
        Frame frame = new Frame("Test");
        GLCapabilities glCapabilities = new GLCapabilities();
        final GLCanvas canvas = new GLCanvas(glCapabilities);
        frame.setSize(256, 256);
        frame.add(canvas);
        frame.setVisible( true );
        canvas.addGLEventListener( this );

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
                System.exit(0);
            }
        });
        canvas.display();
   }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main(GluScaleImage.class.getName());
    }
}
