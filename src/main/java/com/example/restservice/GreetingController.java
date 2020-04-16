package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author ivill
 *
 */

/*
 * Decorador que permite visualizar que el controlador es un controlador de RESTful API
 * por lo que el objeto devuelto siempre sera parseado en un objeto JSON en el HTTP response.
 * El decorador siempre retornara un objeto de dominio y no una vista. (Es como incluir
 * los decoradores @Controller y @ResponseBody)
 */
@RestController 
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	/*
	 * Decorador que permite asegurar que los HTTP GET request a la URI /greeting sean 
	 * Mapeados al metodo greeting()
	 * Otros decoradores que permiten facilitar otros verbos HTTP (POST, PUT, DELETE, PATCH) son:
	 * @PostMapping
	 * @PutMapping
	 * @DeleteMapping
	 * @PatchMapping
	 * 
	 * Existe un decorador universal que es @RequestMapping donde debe pasarse el parametro 
	 * method para especificar el metodo HTTP en uso. Ejemplo: 
	 * 
	 * @RequestMapping(method = RequestMethod.GET, value = "/get/{id}") 
	 */
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
		/*
		 * Decorador @RequestParam enlaza el valor del parametro name (recibido en el request) 
		 * en la string name en el metodo greeting(). Si este parametro esta ausente
		 * el valor por defecto se toma.
		 */
		return new Greeting(counter.incrementAndGet(),String.format(template, name));
		/*
		 * La implementacion del cuerpo del metodo retorna un objeto Greeting con una ID incremental
		 * obtenida del AtomicLong counter y una String formateada correctamente con el template + name
		 */
	}

}
