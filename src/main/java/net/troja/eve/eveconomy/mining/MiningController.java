package net.troja.eve.eveconomy.mining;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MiningController {
    @RequestMapping("/mining")
    public String mining(@RequestParam(value = "name", required = false, defaultValue = "World") final String name,
            final Model model) {
        model.addAttribute("name", name);
        return "mining";
    }
}
