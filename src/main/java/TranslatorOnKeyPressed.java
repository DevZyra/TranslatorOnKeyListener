import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.awt.*;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TranslatorOnKeyPressed implements NativeKeyListener {
    private boolean control = false, f10 = false;

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL_L) {
            control = true;
            if (f10) {
                try {
                    URI u = new URI("https://translate.google.pl/?hl=pl");

                    Desktop d = Desktop.getDesktop();
                    d.browse(u);
                }
                catch (Exception evt) {
                    System.out.println(evt.getMessage());
                }

            }
        } else if (e.getKeyCode() == NativeKeyEvent.VC_F10) {
            f10 = true;
            if (control) {
                try {
                    URI u = new URI("https://translate.google.pl/?hl=pl");

                    Desktop d = Desktop.getDesktop();
                    d.browse(u);
                }
                catch (Exception evt) {
                    System.out.println(evt.getMessage());
                }
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL_L) {
            control = false;
        } else if (e.getKeyCode() == NativeKeyEvent.VC_F10) {
            f10 = false;
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        //System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }


    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        GlobalScreen.addNativeKeyListener(new TranslatorOnKeyPressed());
    }
}