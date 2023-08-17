import { Transition } from "@headlessui/react";
import { Fragment, useState } from "react";
import { NavLink } from "react-router-dom";
import NavItem from "./NavItem/NavItem";
import DashboardIcon from "./Icons/DashboardIcon";
import StudentsIcon from "./Icons/StudentsIcon";
import FacultiesIcon from "./Icons/FacultiesIcon";
import JobAdsIcon from "./Icons/JobAdsIcon";
import { useAuth } from "@src/contexts/AuthProvider";
import LogoutIcon from "./Icons/LogoutIcon";

export default function SideNav() {
  const [showMobileSideBar, setShowMobileSideBar] = useState(false);
  const { user, logout } = useAuth();

  function openSideNav() {
    setShowMobileSideBar(true);
  }

  function closeSideNav() {
    setShowMobileSideBar(false);
  }

  function handleLogout() {
    logout();
  }

  return (
    <div className="w-72">
      <Transition.Root show={showMobileSideBar} as={Fragment}>
        {/* mobile view navbar */}
        <div className="relative z-50 lg:hidden" onClick={closeSideNav}>
          <Transition.Child
            as={Fragment}
            enter="transition-opacity ease-linear duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="transition-opacity ease-linear duration-300"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-gray-900/80" />
          </Transition.Child>
          <div className="fixed inset-0 flex">
            <Transition.Child
              as={Fragment}
              enter="transition ease-in-out duration-300 transform"
              enterFrom="-translate-x-full"
              enterTo="translate-x-0"
              leave="transition ease-in-out duration-300 transform"
              leaveFrom="translate-x-0"
              leaveTo="-translate-x-full"
            >
              <div className="relative mr-16 flex w-full max-w-xs flex-1">
                <Transition.Child
                  as={Fragment}
                  enter="ease-in-out duration-300"
                  enterFrom="opacity-0"
                  enterTo="opacity-100"
                  leave="ease-in-out duration-300"
                  leaveFrom="opacity-100"
                  leaveTo="opacity-0"
                >
                  <div className="absolute left-full top-0 flex w-16 justify-center pt-5">
                    <button type="button" className="-m-2.5 p-2.5" onClick={closeSideNav}>
                      <span className="sr-only">Close sidebar</span>
                      <svg
                        className="h-6 w-6 text-white"
                        fill="none"
                        viewBox="0 0 24 24"
                        strokeWidth="1.5"
                        stroke="currentColor"
                        aria-hidden="true"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          d="M6 18L18 6M6 6l12 12"
                        />
                      </svg>
                    </button>
                  </div>
                </Transition.Child>

                <div className="flex grow flex-col gap-y-5 overflow-y-auto bg-white px-6 pb-2">
                  <div className="flex h-16 shrink-0 items-center">
                    <img
                      className="h-8 w-auto"
                      src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
                      alt="Your Company"
                    />
                  </div>
                  <nav className="flex flex-1 flex-col">
                    <ul role="list" className="flex flex-1 flex-col gap-y-7">
                      <li>
                        <ul role="list" className="-mx-2 space-y-1">
                          <li>
                            <NavItem to="/dashboard" title="Dsahboard">
                              <DashboardIcon />
                            </NavItem>
                          </li>
                          <li>
                            <NavItem to="/students" title="Students">
                              <StudentsIcon />
                            </NavItem>
                          </li>
                          <li>
                            <NavItem to="/faculties" title="Faculties">
                              <FacultiesIcon />
                            </NavItem>
                          </li>
                          <li>
                            <NavItem to="/job-ads" title="Jobs">
                              <JobAdsIcon />
                            </NavItem>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </nav>
                </div>
              </div>
            </Transition.Child>
          </div>
        </div>
      </Transition.Root>

      {/* movile top navbar */}
      <div className="fixed top-0 left-0 right-0 z-40 flex items-center gap-x-6 bg-white px-4 py-4 shadow-sm sm:px-6 lg:hidden">
        <button
          type="button"
          className="-m-2.5 p-2.5 text-gray-700 lg:hidden"
          onClick={openSideNav}
        >
          <span className="sr-only">Open sidebar</span>
          <svg
            className="h-6 w-6"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth="1.5"
            stroke="currentColor"
            aria-hidden="true"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"
            />
          </svg>
        </button>

        <div className="flex-1 text-sm font-semibold leading-6 text-gray-900">Dashboard</div>
        <a href="#">
          <span className="sr-only">Your profile</span>
          <img
            className="h-8 w-8 rounded-full bg-gray-50"
            src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
            alt=""
          />
        </a>
      </div>

      {/* desktop side nav */}
      <div className="hidden lg:fixed lg:left-0 lg:inset-y-0 lg:z-50 lg:flex lg:w-72">
        <div className="flex grow flex-col gap-y-5 overflow-y-auto border-r border-gray-200 bg-white px-6">
          <div className="flex h-16 shrink-0 items-center">
            <img
              className="h-8 w-auto"
              src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
              alt="Your Company"
            />
          </div>
          <nav className="flex flex-1 flex-col">
            <ul role="list" className="flex flex-1 flex-col gap-y-7">
              <li>
                <ul role="list" className="-mx-2 space-y-1">
                  <li>
                    <NavItem to="/dashboard" title="Dsahboard">
                      <DashboardIcon />
                    </NavItem>
                  </li>
                  <li>
                    <NavItem to="/students" title="Students">
                      <StudentsIcon />
                    </NavItem>
                  </li>
                  <li>
                    <NavItem to="/faculties" title="Faculties">
                      <FacultiesIcon />
                    </NavItem>
                  </li>
                  <li>
                    <NavItem to="/job-ads" title="Jobs">
                      <JobAdsIcon />
                    </NavItem>
                  </li>
                </ul>
              </li>

              <ul className=" mt-auto">
                <li>
                  <NavItem title="Logout" onClick={handleLogout}>
                    <LogoutIcon />
                  </NavItem>
                </li>
                <li>
                  <NavLink
                    to="/profile"
                    className={({ isActive }) =>
                      isActive
                        ? "flex items-center gap-x-4 cursor-pointer px-2 py-3 text-sm font-semibold leading-6 text-gray-900 bg-gray-50"
                        : "flex items-center gap-x-4 cursor-pointer px-2 py-3 text-sm font-semibold leading-6 text-gray-900 hover:bg-gray-50"
                    }
                  >
                    <img
                      className="h-8 w-8 rounded-full bg-gray-50"
                      src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                      alt=""
                    />

                    <span className="sr-only">Your profile</span>
                    <span aria-hidden="true">
                      {user?.firstName} {user?.lastName}
                    </span>
                  </NavLink>
                </li>
              </ul>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  );
}
