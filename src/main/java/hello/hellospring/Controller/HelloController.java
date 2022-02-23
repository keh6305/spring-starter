package hello.hellospring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController
{
	@GetMapping("hello")
	public String hello(Model model)
	{
		model.addAttribute("data", "hello!!!");
		
		return "hello";
	}
	
	@GetMapping("hello-mvc")
	public String helloMvc(Model model, @RequestParam(required = false, value = "name") String name)
	{
		model.addAttribute("name", name);
		
		return "hello-template";
	}
	
	@ResponseBody
	@GetMapping("hello-string")
	public String helloString(@RequestParam("name") String name)
	{
		return "hello " + name;
	}
	
	@ResponseBody
	@GetMapping("hello-api")
	public Hello helloApi(@RequestParam("name") String name)
	{
		Hello hello = new Hello();
		hello.setName(name);
		
		return hello;
	}
	
	static class Hello
	{
		private String name;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}
	}
}