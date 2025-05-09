package de.agiehl.bga.BgaStatisticToBggCommentConverter.controller;

import de.agiehl.bga.BgaStatisticToBggCommentConverter.models.ConverterJob;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.service.ConverterService;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.service.ParseBgaStringService;
import de.agiehl.bga.BgaStatisticToBggCommentConverter.service.converter.ConvertStyleTypes;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@AllArgsConstructor
public class WebController {

	private ParseBgaStringService parseService;

	private ConverterService converterService;

	private Environment env;

	@GetMapping(path = "/")
	public String index() {
		return "index";
	}

	@PostMapping(path = "/convert")
	public String convert(@RequestParam(value = "toConvert") String statisticToConvert, Model model,
			HttpSession session) {
		ConverterJob converterJob = parseService.convert(statisticToConvert);

		model.addAttribute("job", converterJob);
		model.addAttribute("style", ConvertStyleTypes.BGG_STYLE.name());
		session.setAttribute("job", converterJob);

		env.getProperty("statistic.default.enabled", List.class);

		return "chooseStats";
	}

	@PostMapping(path = "/result")
	public String result(@RequestParam(value = "entry") List<String> choosenStats,
			@RequestParam(value = "player") List<String> choosenPlayer,
			@RequestParam(value = "style") ConvertStyleTypes style, @RequestParam Map<String, String> allValues,
			@SessionAttribute(name = "job") ConverterJob job, Locale locale, Model model) {

		job.getPlayer().forEach(player -> player.setDisplayName(allValues.get(player.getName())));

		String convertedString = converterService.convert(job, style, choosenStats, choosenPlayer, locale);

		model.addAttribute("result", convertedString);

		return "result";
	}

}