package com.vapehud;

import com.vapehud.modules.ModuleBase;
import java.util.*;

public class ModuleManager {
    private static final List<ModuleBase> modules = new ArrayList<>();

    public static void init() {
        modules.add(new com.vapehud.modules.KillAura());
        modules.add(new com.vapehud.modules.Sprint());
        modules.add(new com.vapehud.modules.NoFall());
        modules.add(new com.vapehud.modules.Scaffold());
        modules.add(new com.vapehud.modules.Speed());
    }

    public static List<ModuleBase> getModules() { return modules; }

    public static List<ModuleBase> getEnabled() {
        return modules.stream().filter(ModuleBase::isEnabled).toList();
    }

    public static ModuleBase getByName(String name) {
        return modules.stream()
            .filter(m -> m.getName().equalsIgnoreCase(name))
            .findFirst().orElse(null);
    }
}
