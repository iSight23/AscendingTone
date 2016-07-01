
import javax.sound.midi.*;

public class AscendingLoop {

	public static void main(String[] args) throws InterruptedException {
		
		int startNote = Integer.parseInt(args[0]);
		int endNote = Integer.parseInt(args[1]);
		int instrument = Integer.parseInt(args[2]);
		
		AscendingLoop loop = new AscendingLoop();
		if (args.length < 3) {
			System.out.println("Remember to use three inputs");
		} else {
			while (startNote < endNote) {
				loop.play(startNote, instrument);
				startNote++;
				Thread.sleep(1000);
			}
			
		}
	}
	
	public void play (int startNoteArg, int instrumentArg) {
		try {
			
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			ShortMessage first = new ShortMessage();
			first.setMessage(192, 1, instrumentArg, 0);
			MidiEvent a = new MidiEvent(first, 0);
			track.add(a);
			
			ShortMessage startPitch = new ShortMessage();
			startPitch.setMessage(144, 1, startNoteArg, 100);
			MidiEvent b = new MidiEvent(startPitch, 1);
			track.add(b);
			
			ShortMessage endPitch = new ShortMessage();
			endPitch.setMessage(128, 1, startNoteArg, 100);
			MidiEvent c = new MidiEvent(endPitch, 10);
			track.add(c);
			
			sequencer.setSequence(seq);
			sequencer.start();
				
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}
}