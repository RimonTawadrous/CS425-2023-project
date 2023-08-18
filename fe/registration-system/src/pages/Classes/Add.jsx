import { Button, Card, Col, Form, Input, Modal, Row, Space, message, Select } from "antd";
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

const AddClasses = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [coursesOptions, setCoursesOptions] = useState([]);
  const [facultyOption, setFacultyOption] = useState([]);
  const { id } = useParams();

  const navigate = useNavigate();

  useEffect(() => {
    const fetchFaculties = async () => {
      const result = await AxiosInstance.get("faculties");
      setFacultyOption(result.data.map((x) => ({ label: x.user.username, value: x.id })));
    };
    const fetchCourses = async () => {
      const result = await AxiosInstance.get("courses");
      setCoursesOptions(result.data.map((x) => ({ label: x.courseName, value: x.id })));
    };

    fetchFaculties();
    fetchCourses();
  }, []);

  useEffect(() => {
    if (id !== "add") {
      getFacultyData();
    }
  }, []);

  const getFacultyData = async () => {
    const { data } = await AxiosInstance.get(`/classes/${id}`);
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
          <Card title={"Add Class"}>
            <Row justify={"space-between"} align={"middle"} gutter={[16, 16]}>
              <Col md={12} xs={24}>
                <Form.Item label="Building Nane" name={["buildingName"]} rules={rules.required}>
                  <Input />
                </Form.Item>
              </Col>

              <Col md={12} xs={24}>
                <Form.Item label="Room Number" name={["roomNumber"]} rules={rules.required}>
                  <Input />
                </Form.Item>
              </Col>
            </Row>
            <Row justify={"space-between"} align={"middle"} gutter={[16, 16]}>
              <Col md={12} xs={24}>
                <Form.Item label="Course" name={["courseId"]} rules={rules.required}>
                  <Select
                    id="courseId"
                    name="courseId"
                    placeholder="Course"
                    label="Course"
                    options={coursesOptions}
                  />
                </Form.Item>
              </Col>

              <Col md={12} xs={24}>
                <Form.Item label="Faculty" name={["facultyId"]} rules={rules.required}>
                  <Select
                    id="facultyId"
                    name="facultyId"
                    placeholder="Faculty"
                    label="Faculty"
                    options={facultyOption}
                  />
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
                      navigate("/classes");
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
                      await AxiosInstance.post("/classes", values);
                    } else {
                      const values = await form.validateFields();
                      values.id = id;
                      await AxiosInstance.put(`/classes/${id}`, values);
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

export default AddClasses;
