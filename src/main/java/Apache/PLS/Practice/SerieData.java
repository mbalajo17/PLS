package Apache.PLS.Practice;

import lombok.Data;
import org.apache.poi.xddf.usermodel.PresetColor;

import java.util.List;
@Data
public class SerieData {
    // serie name
    private String name;
    // serie data
    private List<Number> data;
    // serie color
    private PresetColor color = PresetColor.ALICE_BLUE;
}