package Services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static AuditService instance;

    FileWriter fileWriter;

    DateTimeFormatter timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AuditService()
    {
        try
        {
            this.fileWriter = new FileWriter("src\\main\\java\\Files\\Audit.csv");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static AuditService getInstance()
    {
        if(instance == null)
            instance = new AuditService();

        return instance;
    }

    public void logAction(String action) throws IOException
    {
        fileWriter.append(action);
        fileWriter.append(",");
        fileWriter.append(timestamp.format(LocalDateTime.now()));
        fileWriter.append("\n");
        fileWriter.flush();
    }
}






















