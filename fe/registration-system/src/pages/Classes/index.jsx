import { useEffect, useState } from "react";
import { Row, Card, Form, Table, Button, Space, Modal } from "antd";
import { DeleteOutlined, EditOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import AxiosInstance from "@src/axios/AxiosInstance";

const Classes = () => {
  const [list, setList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [, setPage] = useState(0);
  const [, setCurrentState] = useState(1);

  const navigate = useNavigate();

  const [form] = Form.useForm();

  const columns = [
    {
      title: "Id",
      dataIndex: "id",
    },
    {
      title: "course",
      dataIndex: "roomNumber",
    },
    {
      title: "buildingName",
      dataIndex: "buildingName",
    },
    {
      title: "Course",
      dataIndex: "course",
      render: (course) => {
        console.log(course);
        return `${course?.courseName}`;
      },
    },
    {
      title: "Faculty",
      dataIndex: "faculty",
      render: (faculty) => {
        return `${faculty?.user?.firstName} ${faculty?.user?.lastName}`;
      },
    },
    {
      label: "",
      dataIndex: "id",
      render: (id, record) => (
        <Space>
          <Button
            shape="circle"
            icon={<EditOutlined />}
            onClick={() => {
              navigate(`${id}`, {
                state: record,
              });
            }}
          />

          <Button
            shape="circle"
            danger
            icon={<DeleteOutlined />}
            onClick={() => {
              Modal.confirm({
                title: `do you want to delete ${record.name}? permentatly`,
                onOk: async () => {
                  await deleteItem(id);
                },
              });
            }}
          />
        </Space>
      ),
    },
  ];

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    setLoading(true);
    const { data } = await AxiosInstance.get("/classes");
    setList(data);
    setLoading(false);
  };

  const deleteItem = async (id) => {
    await AxiosInstance.delete(`/classes/${id}`);
    loadData();
  };

  return (
    <>
      <Card
        title={"Classes"}
        extra={
          <Button
            onClick={() => {
              navigate("/classes/add");
            }}
          >
            Add Course
          </Button>
        }
      >
        <Form
          form={form}
          layout="vertical"
          onFieldsChange={(changed) => {
            console.log(changed.at(0)?.name);
            if (changed.at(0)?.name.toString() === "state") {
              console.log("city");
              setCurrentState(changed.at(0)?.value);
            }
          }}
        >
          <Row justify={"space-between"} align={"middle"} gutter={[16, 16]}></Row>
        </Form>
      </Card>

      <Table
        rowKey={"id"}
        columns={columns}
        loading={loading}
        onChange={(pagination) => {
          setPage(pagination.current);
        }}
        dataSource={list}
      />
    </>
  );
};

export default Classes;
