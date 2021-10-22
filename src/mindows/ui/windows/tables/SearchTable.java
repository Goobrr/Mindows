package mindows.ui.windows.tables;

import arc.func.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.ui.*;

public class SearchTable extends Table{
    public TextField search;
    public Table all = new Table().top().left();
    public Cons2<Table, String> rebuild;
    float lastX, lastY, lastW, lastH;

    public SearchTable(String def, Cons2<Table, String> rebuild){
        super(Styles.black5);

        search = new TextField(def);
        search.changed(this::rebuild);
        this.rebuild = rebuild;

        rebuild();
        //search field
        table(Tex.button, s -> s.add(search)).growX().pad(10f).top().left();
        row();
        table(Styles.black5, a -> {
            a.top().left();
            a.pane(Styles.defaultPane, pane -> pane.add(all)).grow().scrollX(true);
        }).grow();
    }

    public SearchTable(Cons2<Table, String> rebuild){
        this("", rebuild);
    }

    void rebuild(){
        if(parent != null){
            lastX = parent.x;
            lastY = parent.y;
            lastW = parent.getWidth();
            lastH = parent.getHeight();
            Log.info(lastX);
            Log.info(lastY);
            Log.info(lastW);
            Log.info(lastH);
            Log.info("---");
        }
        all.clear();
        rebuild.get(all, search.getText());
        if(parent != null){
            parent.moveBy(lastX, lastY);
            parent.sizeBy(lastW, -lastH);
            parent.moveBy(0, lastH);
            Log.info(lastX);
            Log.info(lastY);
            Log.info(lastW);
            Log.info(lastH);
            Log.info("=====================");
        }
    }
}