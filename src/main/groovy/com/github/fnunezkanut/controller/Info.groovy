package com.github.fnunezkanut.controller

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody


@Controller
@EnableAutoConfiguration
class Info {


	@RequestMapping(
		value = "/info",
		method = RequestMethod.GET,
		produces = "application/json"
	)
	@ResponseBody
	HashMap<String, String> info() {

		final Logger logger = LoggerFactory.getLogger(Info.class)
		final String url = 'http://10.0.0.13:8080'


		final client = new HTTPBuilder(url)

		def res = client.request( Method.GET, ContentType.JSON  ) {

			uri.path = '/info'

			response.success = { resp, json ->

				return  json
			}
			response.failure = { resp, json ->

				logger.error("fail ${resp.statusLine}")
			}
		}


		final HashMap<String, String> message = res  as HashMap<String,String>

		return message
	}


}
