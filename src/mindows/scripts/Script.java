package mindows.scripts;

import arc.files.*;
import mindustry.Vars;
import mindustry.gen.Call;

import java.util.regex.*;

// container class for scripts
public class Script {
    public static Pattern namePattern = Pattern.compile("(?<=//NAME \").*(?!\\n)");
    public static Pattern descPattern = Pattern.compile("(?<=//DESCRIPTION \").*(?!\\n)");
    public String name, description, code;
    public Fi file;

    public Script(Fi file){
        this(file.nameWithoutExtension(), file);
    }

    public Script(String name, Fi file){
        this.file = file;
        this.code = file.readString();

        this.name = getName(code, name);
        this.description = getDesc(code, "");
    }

    public String run(){
        if(Vars.net.client()){
            if(Vars.player.admin){
                Call.sendChatMessage("/js " + code);
                return "executed";
            }
            return "[scarlet]Scripts disabled[]";
        }
        return Vars.mods.getScripts().runConsole(code);
    }

    public static String getName(String input, String def){
        Matcher nameMatcher = namePattern.matcher(input);
        if(nameMatcher.find()){
            return nameMatcher.group(nameMatcher.groupCount());
        }else return def;
    }

    public static String getDesc(String input, String def){
        Matcher descMatcher = descPattern.matcher(input);
        if(descMatcher.find()){
            return descMatcher.group(descMatcher.groupCount());
        }else return def;
    }
}
