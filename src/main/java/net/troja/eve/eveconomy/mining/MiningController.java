package net.troja.eve.eveconomy.mining;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.troja.eve.eveconomy.account.Account;

@Controller
public class MiningController {
    @RequestMapping("/mining")
    public String mining(@RequestParam(value = "name", required = false, defaultValue = "World") final String name,
            final Model model) {
        final Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", account.getCharacterName());
        return "mining";
    }
}
