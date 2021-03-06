package name.wadewalker.jogl1tests.onetriangle;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLCapabilities;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A minimal program that draws with JOGL in an AWT Frame.
 *
 * @author Wade Walker
 */
public class OneTriangleAWT {

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
            public void displayChanged( GLAutoDrawable glautodrawable, boolean modeChanged, boolean deviceChanged ) {
            }
        });

        final Frame frame = new Frame( "One Triangle AWT" );
        frame.add( glcanvas );
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
                frame.remove( glcanvas );
                frame.dispose();
                System.exit( 0 );
            }
        });

        frame.setSize( 640, 480 );
        frame.setVisible( true );
    }
}
