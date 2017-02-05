package net.troja.eve.eveconomy.pve;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PveController {
    @RequestMapping("/pve/new")
    public String create(@RequestParam(value = "name", required = false, defaultValue = "World") final String name,
            final Model model) {
        model.addAttribute("name", name);
        return "pve";
    }

    @RequestMapping("/pve/add")
    public String add(@RequestParam(value = "name", required = false, defaultValue = "World") final String name,
            final Model model) {
        model.addAttribute("name", name);
        return "pve";
    }
}
