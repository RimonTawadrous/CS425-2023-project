import "./App.css";
import { Route, Routes } from "react-router-dom";
import UnAuthLayout from "@src/layouts/UnAuthLayout/UnAuthLayout";
import MainLayout from "@src/layouts/MainLayout/MainLayout";
import NoPage from "./pages/NoPage";
import Login from "./pages/Login";
import { AuthProvider } from "./Auth/AuthProvider";
import Students from "./pages/Students";
import AddStudent from "./pages/Students/Add";
import Faculties from "./pages/Faculties";
import AddFaculty from "@src/pages/Faculties/Add";
import Courses from "./pages/Courses";
import AddCourse from "@src/pages/Courses/Add";
import Classes from "./pages/Classes";
import AddClasses from "@src/pages/Classes/Add";
function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route path="students" element={<Students />} />
          <Route path="students/:id" element={<AddStudent />} />
          <Route path="faculties" element={<Faculties />} />
          <Route path="faculties/:id" element={<AddFaculty />} />
          <Route path="courses" element={<Courses />} />
          <Route path="courses/:id" element={<AddCourse />} />
          <Route path="classes" element={<Classes />} />
          <Route path="classes/:id" element={<AddClasses />} />
          {/*
          <Route path="profile" element={<Profile />} />
          <Route path="profile/edit" element={<EditProfile />} />
          <Route path="profile/change-password" element={<ChangePassword />} />
          <Route path="faculties/:facultyId" element={<FacultyDetails />} />
          <Route path="faculties/:facultyId/reset-password" element={<FacultyResetPassword />} />

          <Route path="faculties/add" element={<AddFaculties />} />
          <Route path="job-ads" element={<JobAvertisments />} />
          <Route path="job-ads/:id" element={<AddJobAd />} />
          <Route path="job-ads/add" element={<AddJobAd />} /> */}

          <Route path="*" element={<NoPage />} />
        </Route>

        <Route path="/" element={<UnAuthLayout />}>
          <Route index element={<Login />} />
          <Route path="login" element={<Login />} />
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </AuthProvider>
  );
}

export default App;
