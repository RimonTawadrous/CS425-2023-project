import { Outlet, Navigate } from "react-router-dom";
import { useAuth } from "@src/Auth/AuthProvider";
import SideNav from "../../generic/SideNav";

const MainLayout = () => {
  const { user } = useAuth();

  if (!user) {
    return <Navigate to="/login" />;
  }

  return (
    <>
      <SideNav />

      <main className="pt-20 lg:pt-5 lg:pl-72">
        <div className="">
          <Outlet />
        </div>
      </main>
    </>
  );
};

export default MainLayout;
