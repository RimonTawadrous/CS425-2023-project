import { Button, Card, Col, Form, Input, Modal, Row, Space, message } from "antd";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AxiosInstance from "@src/axios/AxiosInstance";

const rules = {
  email: [
    {
      required: true,
      message: "The E-mail! is mandatory",
    },
    {
      type: "email",
      message: "The input is not valid E-mail!",
    },
  ],
  required: [
    {
      required: true,
      message: "this field is required",
    },
  ],
};

const AddFaculty = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const { id } = useParams();

  const navigate = useNavigate();

  useEffect(() => {
    if (id !== "add") {
      getFacultyData();
    }
  }, []);

  const getFacultyData = async () => {
    const { data } = await AxiosInstance.get(`/faculties/${id}`);
    form.setFieldsValue({
      ...data,
      lastName: data?.user?.lastName,
      firstName: data?.user?.firstName,
      email: data?.user?.email,
      middleName: data?.user?.middleName,
      username: data?.user?.username,
    });
    console.log(data);
  };

  return (
    <div
      style={{
        backgroundColor: "#D3D3D3",
        padding: "25px",
      }}
    >
      <Form form={form} layout="vertical">
        <Space className="w-full" direction="vertical">
          <Card title={"Add Faculty"}>
            <Row justify={"space-between"} align={"middle"} gutter={[16, 16]}>
              <Col md={12} xs={24}>
                <Form.Item label="last name" name={["lastName"]} rules={rules.required}>
                  <Input />
                </Form.Item>
              </Col>

              <Col md={12} xs={24}>
                <Form.Item label="first name" name={["firstName"]} rules={rules.required}>
                  <Input />
                </Form.Item>
              </Col>

              <Col md={12} xs={24}>
                <Form.Item label="middle name" name={["middleName"]} rules={rules.required}>
                  <Input />
                </Form.Item>
              </Col>
            </Row>
          </Card>

          <Card title="Authentication">
            <Row gutter={16}>
              <Col md={12} xs={24}>
                <Form.Item label="username" name="username" rules={rules.required}>
                  <Input />
                </Form.Item>
              </Col>

              <Col md={12} xs={24}>
                <Form.Item label="Email" name="email" rules={rules.email}>
                  <Input />
                </Form.Item>
              </Col>

              <Col md={12} xs={24}>
                <Form.Item label="Password" name="password" rules={rules.required}>
                  <Input.Password />
                </Form.Item>
              </Col>
            </Row>
          </Card>

          <Row justify="space-between">
            <Col>
              <Button
                danger
                onClick={() => {
                  Modal.confirm({
                    title: "do you want togo back to the list?",
                    onOk: () => {
                      // form.setFieldsValue({})
                      navigate("/students");
                    },
                  });
                }}
              >
                Reset
              </Button>
            </Col>
            <Col>
              <Button
                loading={loading}
                onClick={async () => {
                  try {
                    setLoading(true);
                    if (id === "add") {
                      let values = await form.validateFields();
                      await AxiosInstance.post("/faculties", values);
                    } else {
                      const values = await form.validateFields();
                      values.id = id;
                      await AxiosInstance.put(`/faculties/${id}`, values);
                    }
                    setLoading(false);
                  } catch (e) {
                    setLoading(false);
                    message.error("an error has accurred");
                  }
                }}
              >
                Save
              </Button>
            </Col>
          </Row>
        </Space>
      </Form>
    </div>
  );
};

export default AddFaculty;
