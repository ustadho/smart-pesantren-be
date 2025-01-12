package id.smartpesantren.web;

import id.smartpesantren.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/academic/subject-schedule/report")
public class SubjectScheduleReportResource {
    @Autowired
    ReportService reportService;

    @Autowired
    private ResourceLoader resourceLoader;

    private final Logger logger = LoggerFactory.getLogger(SubjectScheduleReportResource.class);

    @RequestMapping(value = "by-classroom*", method = RequestMethod.GET)
    private void printNota(HttpServletRequest request, HttpServletResponse response, @RequestHeader("Timezone") String timeZone) throws ParseException, IOException {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);

        String classRoomId = request.getParameter("crid");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("classRoomId", classRoomId);
        parameters.put("timezone", timeZone);

        reportService.generateReport("ac-subject-schedule", format, parameters, response, "ac-subject-schedule-" + classRoomId);
    }

}
