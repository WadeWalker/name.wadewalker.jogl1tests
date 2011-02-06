package name.wadewalker.jogl1tests.onetriangle;

import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;

/**
 * A minimal program that draws with JOGL in an SWT Composite using an
 * embedded AWT Frame and the AWT GLCanvas via the SWT-AWT bridge.
 *
 * @author Wade Walker
 */
public class OneTriangleSWTAWTBridge {

    public static void main( String [] args ) {
        GLCapabilities glcapabilities = new GLCapabilities();
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );

        Display display = new Display();
        final Shell shell = new Shell( display );
        shell.setText( "OneTriangle SWT AWT Bridge" );
        shell.setLayout( new FillLayout() );
        shell.setSize( 640, 480 );

        final Composite composite = new Composite( shell, SWT.EMBEDDED );
        composite.setLayout( new FillLayout() );

        Frame frame = SWT_AWT.new_Frame( composite );
        frame.add( glcanvas );

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

        shell.open();

        while( !shell.isDisposed() ) {
            if( !display.readAndDispatch() )
                display.sleep();
        }

        display.dispose();
    }
}

