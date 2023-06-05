package com.ipi.jva320.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

//@Controller
//public class CustomErrorController implements ErrorController {
//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request, ModelMap model) {
////        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
////        Object content = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
////
////        model.addAttribute("errorContent", content);
////        model.addAttribute("errorStatus", status);
//        return "not_found";
//    }
//}