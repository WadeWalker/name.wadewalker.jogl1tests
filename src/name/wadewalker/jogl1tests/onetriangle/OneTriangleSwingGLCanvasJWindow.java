package name.wadewalker.jogl1tests.onetriangle;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLCapabilities;
import javax.swing.JWindow;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A minimal program that draws with JOGL in a Swing JWindow using the AWT GLCanvas.
 *
 * @author Wade Walker
 */
public class OneTriangleSwingGLCanvasJWindow {

    public static void main( String [] args ) {
        GLCapabilities glcapabilities = new GLCapabilities();
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );

        glcanvas.addGLEventListener( new GLEventListener() {
            
            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
                OneTriangle.setup( glautodrawable.getGL(), width, height );
            }
            
            @Override
            public void init( GLAutoDrawable glautodrawable ) {
            }
            
            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                OneTriangle.render( glautodrawable.getGL(), glautodrawable.getWidth(), glautodrawable.getHeight() );
            }

            @Override
            public void displayChanged( GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged ) {
            }
        });

        // "One Triangle Swing GLCanvas JWindow" 
        final JWindow jwindow = new JWindow(); 
        jwindow.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
                jwindow.dispose();
                System.exit( 0 );
            }
        });

        jwindow.getContentPane().add( glcanvas, BorderLayout.CENTER );
        jwindow.setSize( 640, 480 );
        jwindow.setVisible( true );
    }
}
