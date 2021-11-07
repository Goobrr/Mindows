package mindows.ui.tables;

import arc.func.*;
import arc.scene.ui.layout.*;
import mindows.ui.*;
import mindustry.*;
import mindustry.ui.*;

public class TaskbarTable extends Table{
    public static Boolp visibility = () -> Vars.ui.hudfrag.shown && !Vars.ui.minimapfrag.shown();

    public TaskbarTable(WindowTable... items){
        visible = true;
        table(MindowsTex.sidebar,t -> {
            t.top().center();
            for(WindowTable w : items){
                t.button(w.icon, Styles.emptyi, () -> {
                    w.visible(visibility);
                }).disabled(b -> w.visible)
                    .size(40f)
                    .padLeft(5f)
                    .tooltip(w.name);
                t.row();
            }
        }).right().center().width(40f);
    }
}