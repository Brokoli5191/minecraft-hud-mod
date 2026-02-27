package com.vapehud;

import com.vapehud.modules.*;
import java.util.*;

public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new KillAura());
        modules.add(new Sprint());
        modules.add(new NoFall());
        modules.add(new Scaffold());
        modules.add(new Speed());
    }

    public static List<Module> getModules() { return modules; }

    public static List<Module> getEnabled() {
        return modules.stream().filter(Module::isEnabled).toList();
    }

    public static Module getByName(String name) {
        return modules.stream()
            .filter(m -> m.getName().equalsIgnoreCase(name))
            .findFirst().orElse(null);
    }
}
