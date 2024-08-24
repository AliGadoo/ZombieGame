package Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL[] soundURL = new URL[10];
    private FloatControl volumeControl;
    private boolean isMuted = false;
    private float previousVolume = 0.0f;

    public Sound() {
        soundURL[0] = getClass().getResource("../Game/Gui/StartSound.wav");
        soundURL[3] = getClass().getResource("../Assets/Sound/Click.wav");
        soundURL[4] = getClass().getResource("../Assets/Sound/Bullets/pistol.wav");
        soundURL[5] = getClass().getResource("../Assets/Sound/Bullets/emptygun.wav");
        soundURL[6] = getClass().getResource("../Assets/Sound/Bullets/rifleReload.mp3");
        soundURL[7] = getClass().getResource("../Assets/Sound/Zombies/zombieBite.wav");
        soundURL[8] = getClass().getResource("../Assets/Sound/Zombies/zombiehit.wav");
        soundURL[9] = getClass().getResource("../Assets/Sound/Zombies/Zombies.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop(); // إيقاف الصوت فورًا
            clip.flush(); // تفريغ البيانات المؤقتة
            clip.close(); // إغلاق الـ Clip لضمان الإيقاف
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void mute() {
        if (clip != null && volumeControl != null) {
            if (!isMuted) {
                previousVolume = volumeControl.getValue();
                clip.stop(); // إيقاف التشغيل
                volumeControl.setValue(volumeControl.getMinimum()); // خفض الصوت
                isMuted = true;
            } else {
                volumeControl.setValue(previousVolume); // استعادة مستوى الصوت
                clip.start(); // تشغيل الصوت
                isMuted = false;
            }
        }
    }
}
