package controller;

import dao.DoctorDAO;
import model.Doctor;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/doctor")
public class DoctorServlet extends HttpServlet {
    private DoctorDAO dao;

    @Override
    public void init() {
        dao = new DoctorDAO();
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
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteDoctor(id);
            response.sendRedirect("doctor");
            return;
        }

        List<Doctor> list = dao.getAllDoctors();
        request.setAttribute("doctorList", list);
        request.getRequestDispatcher("doctor-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("doctorName");
        String spec = request.getParameter("specialization");
        String phone = request.getParameter("phone");
        int deptId = Integer.parseInt(request.getParameter("departmentId"));

        dao.addDoctor(name, spec, phone, deptId);
        response.sendRedirect("doctor");
    }
}
