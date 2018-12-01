package audio;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido {
         Clip sonido;

		public Sonido(){
			try {
				sonido = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
         public void abrir(File a) {
			try {
				sonido.open(AudioSystem.getAudioInputStream(a));
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
         
         public void reproducir() {
        	 sonido.start();
         }
         
         public void detener() {
        	 sonido.stop();
         }

}