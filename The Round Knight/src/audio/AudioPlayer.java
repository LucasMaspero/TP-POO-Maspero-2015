package audio;

import javax.sound.sampled.*;

public class AudioPlayer {
	
	private Clip clip;

	public AudioPlayer(String s) {
		
		try {
			
			// Cargar archivo de audio
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(	AudioFormat.Encoding.PCM_SIGNED,
														baseFormat.getSampleRate(),
														16,
														baseFormat.getChannels(),
														baseFormat.getChannels()*2,
														baseFormat.getSampleRate(),
														false );
			
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat,ais);
			
			clip = AudioSystem.getClip();
			clip.open(dais);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		
		// Reproducir archivo de audio
		if(clip != null) {
			stop();
			clip.setFramePosition(0); // Desde el principio
			clip.start();
		}
		
 	}
	
	public void stop() {
		
		if(clip.isRunning())
			clip.stop();
	
	}
	
	public void close() {
	
		stop();
		clip.close();
	
	}
	
}
