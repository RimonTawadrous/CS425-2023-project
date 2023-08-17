import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../../Auth/AuthProvider";

const UnAuthLayout = () => {
  const { user } = useAuth();

  if (user) {
    return <Navigate to="/dashboard" />;
  }

  return <Outlet />;
};

export default UnAuthLayout;
