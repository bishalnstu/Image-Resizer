package imageResizer;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NativeInterface.open();
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		Resizer obj = new Resizer();
        
		NativeInterface.runEventPump();
	}
}
