package com.mitocode.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
@Order(-1)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler{

	public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
		super(errorAttributes, resourceProperties, applicationContext);
		this.setMessageWriters(configurer.getWriters());
	}
	
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
		Map<String, Object> errorGeneral = getErrorAttributes(request, false);
		Map<String, Object> mapException = new HashMap<>();
		
		var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		String statusCode = String.valueOf(errorGeneral.get("status"));
			
		//Devolver message de valid
		//String mensaje = String.valueOf(errorGeneral.get("message"));		
		
		switch (statusCode) {
		case "500":
			mapException.put("error", "500");
			mapException.put("excepcion", "Error general del backend");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			break;
		case "400":
			mapException.put("error", "400");
			mapException.put("excepcion", "Petición incorrecta");
			httpStatus = HttpStatus.BAD_REQUEST;
			break;
		case "401":
			mapException.put("error", "401");
			mapException.put("excepcion", "No Autorizado");
			httpStatus = HttpStatus.UNAUTHORIZED;
			break;
		case "404":
			mapException.put("error", "404");
			mapException.put("excepcion", "Recurso no encontrado");
			httpStatus = HttpStatus.NOT_FOUND;
			break;
		case "418":
			mapException.put("error", "418");
			mapException.put("excepcion", "Soy una tetera T_T");
			httpStatus = HttpStatus.I_AM_A_TEAPOT;
			break;
		default:
			mapException.put("error", "900");
			mapException.put("excepcion", errorGeneral.get("error"));
			httpStatus = HttpStatus.CONFLICT;
			break;
		}

		return ServerResponse.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(errorGeneral));
	}
}
