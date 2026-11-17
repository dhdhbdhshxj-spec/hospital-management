package controller;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.PatientDAO;
import model.Appointment;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {
    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;

    @Override
    public void init() {
        appointmentDAO = new AppointmentDAO();
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("confirm".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            appointmentDAO.updateStatus(id, "Confirmed");
            response.sendRedirect("appointment");
            return;
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            appointmentDAO.deleteAppointment(id);
            response.sendRedirect("appointment");
            return;
        }

        request.setAttribute("appointmentList", appointmentDAO.getAllAppointments());
        request.setAttribute("patientList", patientDAO.getAllPatients());
        request.setAttribute("doctorList", doctorDAO.getAllDoctors());
        request.getRequestDispatcher("appointment-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        Date date = Date.valueOf(request.getParameter("appointmentDate"));
        String timeStr = request.getParameter("appointmentTime");
        if (timeStr.length() == 5) {
            timeStr += ":00";
        }
        Time time = Time.valueOf(timeStr);
        String status = request.getParameter("status");

        Appointment a = new Appointment(patientId, doctorId, date, time, status);
        appointmentDAO.addAppointment(a);
        response.sendRedirect("appointment");
    }
}
