import { NavLink } from "react-router-dom";

const NavItem = ({ to, title, children, onClick }) => {
  if (to) {
    return (
      <NavLink
        to={to}
        className={({ isActive }) =>
          isActive
            ? "text-indigo-600 bg-gray-50 flex gap-x-3 rounded-md p-2 text-sm leading-6 font-semibold"
            : "text-gray-700 hover:text-indigo-600 hover:bg-gray-50 group flex gap-x-3 rounded-md p-2 text-sm leading-6 font-semibold"
        }
      >
        {children}
        {title}
      </NavLink>
    );
  }
  if (onClick) {
    return (
      <div
        onClick={onClick}
        className="text-gray-700 hover:text-indigo-600 hover:bg-gray-50 group flex gap-x-3 rounded-md p-2 text-sm leading-6 font-semibold"
      >
        {children}
        {title}
      </div>
    );
  }
};

export default NavItem;
