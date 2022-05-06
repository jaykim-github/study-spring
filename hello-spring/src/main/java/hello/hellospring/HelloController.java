package hello.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@GetMapping("hello")
	 public String hello(Model model) {
	 model.addAttribute("data", "hello!!");
	 return "hello";
	}
	
	//get parameter 받기
	@GetMapping("hello-mvc")
	 public String helloMvc(@RequestParam("name") String name, Model model) {
	 model.addAttribute("name", name);
	 return "hello-template";
	 }
	
	
	//뷰 리졸버를 사용하지 않음 대신에 http의 body에 문자내용을 직접 반환
	@GetMapping("hello-string")
	 @ResponseBody
	 public String helloString(@RequestParam("name") String name) {
	 return "hello " + name;
	 }
	
	
	//ResponseBody 객체 반환 - 객체가 json 형태로 반환
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
	
	static class Hello{
		private String name;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}

}
