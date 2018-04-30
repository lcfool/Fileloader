import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String urlString = getInputString();
        String downloadFolderPath = getFolderToSave();
        URL url = createUrlFromInput(urlString);

        String downloadFilename = null;
        if (urlString != null) {
            downloadFilename = urlString.substring(urlString.lastIndexOf("/") + 1, urlString.length());
        } else System.out.println("Oops, seems that URL is missing");

        String filePath = null;
        if (downloadFilename != null) {
            filePath = downloadFolderPath.concat(downloadFilename);
        } else System.out.println("Filepath missing");

        downloadFile(url, filePath);


    }

    //Creates download URL from String
    private static URL createUrlFromInput(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("Incorrect URL");
            e.printStackTrace();
            return null;
        }

    }

    //Get users download path in String
    private static String getInputString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input download URL");
        return scanner.nextLine();
    }

    //Get download destination folder, NOTE: path should end with "/"
    private static String getFolderToSave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input path to download folder");
        return scanner.nextLine();
    }


    //Download file and save it to destination folder
    private static void downloadFile(URL downloadUrl, String filePath) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(downloadUrl.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            byte[] bytesArray = new byte[1024];
            int counter = 0;
            while ((counter = bufferedInputStream.read(bytesArray, 0, 1024)) != -1) {
                fileOutputStream.write(bytesArray, 0, counter);
            }
            bufferedInputStream.close();
            fileOutputStream.close();
            System.out.println("Your file was saved");
        } catch (IOException e) {
            System.out.println("Oops, there was an IO error");
            e.printStackTrace();
        }
    }
}
