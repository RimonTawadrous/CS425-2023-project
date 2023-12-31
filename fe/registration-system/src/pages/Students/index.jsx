import { useEffect, useState } from "react";
import { Row, Card, Form, Table, Button, Space, Modal } from "antd";
import { DeleteOutlined, EditOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import AxiosInstance from "@src/axios/AxiosInstance";

const Students = () => {
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
      title: "Name",
      dataIndex: "user",
      render: (user) => `${user?.firstName} ${user?.lastName}`,
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
    const { data } = await AxiosInstance.get("/students");
    setList(data);
    setLoading(false);
  };

  const deleteItem = async (id) => {
    await AxiosInstance.delete(`/students/${id}`);
    loadData();
  };

  return (
    <>
      <Card
        title={"Students"}
        extra={
          <Button
            onClick={() => {
              navigate("/students/add");
            }}
          >
            Add Student
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
          <Row justify={"space-between"} align={"middle"} gutter={[16, 16]}>
            {/* <Col span={5}>
              <Form.Item label="major" name="major">
                <Select
                  className="w-100"
                  showSearch
                  options={Object.keys(jobSpecialties).map((x) => ({
                    label: x,
                    value: x,
                  }))}
                />
              </Form.Item>
            </Col> */}
          </Row>
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
  // return (
  //   <div className="px-5 container">
  //     <div>
  //       <div className="md:flex md:items-center md:justify-between">
  //         <div className="min-w-0 flex-1">
  //           <h2 className="text-2xl font-bold leading-7 text-gray-900 sm:truncate sm:text-3xl sm:tracking-tight">
  //             Students
  //           </h2>
  //         </div>
  //         <div className="mt-5WW flex md:ml-4 md:mt-0">
  //           <Link
  //             to="/job-ads/add"
  //             className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
  //           >
  //             Add
  //           </Link>
  //         </div>
  //       </div>
  //     </div>

  //     <div className="mt-5">
  //       <StudentCard />
  //     </div>
  //   </div>
  // );
};

export default Students;
