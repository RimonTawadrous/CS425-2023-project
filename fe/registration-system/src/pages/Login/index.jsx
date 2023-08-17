import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../Auth/AuthProvider";
import { Button, Form } from "antd";
import SimpleInput from "../../generic/inputs/SimpleInput/SimpleInput";
import axiosInstance from "@src/axios/AxiosInstance.js";

const Login = () => {
  const { setAuth } = useAuth();
  const navigate = useNavigate();

  const onFinish = async (data) => {
    const response = await axiosInstance.post("http://localhost:8080/api/v1/auth/login", data);
    setAuth(response.data);
    navigate("/dashboard");
  };

  return (
    <div className="flex min-h-full flex-col justify-center py-12 sm:px-6 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-md">
        <img
          className="mx-auto h-10 w-auto"
          src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
          alt="Your Company"
        />
        <h2 className="mt-6 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
          Sign in to your account
        </h2>
      </div>

      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-[480px]">
        <div className="bg-white p-3 py-6 shadow w-96 sm:rounded-lg sm:px-12">
          <Form onFinish={onFinish}>
            <Form.Item name="username" className="w-full">
              <SimpleInput
                id="username"
                type="text"
                placeholder="Username"
                label="Username"
                name="username"
                value=""
              />
            </Form.Item>
            <Form.Item name="password" className="w-full">
              <SimpleInput
                id="password"
                name="password"
                type="password"
                placeholder="password"
                label="Password"
                value=""
              />
            </Form.Item>

            <Form.Item label="" name="username">
              <Button
                type="primary"
                htmlType="submit"
                className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
              >
                Sign in
              </Button>
            </Form.Item>
          </Form>
        </div>

        <p className="mt-10 text-center text-sm text-gray-500">
          Don&apos;t have an account?
          <Link
            to="/signup"
            className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500"
          >
            Sign Up
          </Link>
        </p>
      </div>
    </div>
  );
};

export default Login;
