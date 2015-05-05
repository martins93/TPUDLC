package logics;

import org.mozilla.universalchardet.UniversalDetector;

public class EncodingDetector {

    public String detect(String args) throws java.io.IOException {
        byte[] buf = new byte[4096];
        String fileName = args;
        java.io.FileInputStream fis = new java.io.FileInputStream(fileName);

        UniversalDetector detector = new UniversalDetector(null);

        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        detector.dataEnd();

        String encoding = detector.getDetectedCharset();
        if (encoding == null) {
            encoding = "ISO-8859-1";
        }

        detector.reset();
        return encoding;
    }

}
