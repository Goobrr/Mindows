package mindows.ui.windows;

import mindows.ui.WindowTable;
import mindows.ui.windows.tables.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.gen.Icon;

public class SchematicWindow extends WindowTable{
    public SchematicWindow(){
        super("schematics", Icon.copy, t -> {});
    }

    @Override
    public void build(){
        top();
        topBar();

        SearchTable searchTable = new SearchTable((t, search) -> {
            for(Schematic s : Vars.schematics.all()){
                if(search == null || !search.isEmpty() && !s.name().toLowerCase().replace("\\W", "").contains(search.toLowerCase().replace("\\W", ""))) continue;
                t.labelWrap(s.name()).left();
                t.row();
            }
        });

        add(searchTable).grow();

        resizeButton();
    }
}