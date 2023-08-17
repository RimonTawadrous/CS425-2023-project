import { useEffect, useState } from "react";
import { Student } from "../../types/Student";
import StudentService from "../../serivces/StudentService";
import { Row, Col, Card, Input, Form, Select, Table, Button, Tag, Space, Modal } from "antd";
import { DeleteOutlined, EditOutlined, SearchOutlined } from "@ant-design/icons";
import { jobSpecialties, states } from "@src/assets/data";
import { useNavigate } from "react-router-dom";
import SimpleSelectMenu from "@src/generic/inputs/SimpleSelectMenu/SimpleSelectMenu";
import AxiosInstance from "@src/src/axios/AxiosInstance";

const Students = () => {
  const [list, setList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searchValues, setSearchValues] = useState < any > {};
  const [page, setPage] = useState(0);
  const [currentState, setCurrentState] = useState(1);


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
      render: (user: any) => `${user?.firstName} ${user?.lastName}`,
    },
    {
      title: "State",
      dataIndex: "user",
      render: (user: any) => user?.state?.name,
    },
    {
      title: "City",
      dataIndex: "user",
      render: (user: any) => user?.city?.name,
    },
    {
      label: "Major",
      dataIndex: "major",
      render: (major: any) => <Tag color={"blue-inverse"}>{major}</Tag>,
    },

    {
      label: "",
      dataIndex: "id",
      render: (id: string, record: any) => (
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
    loadData(
      page,
      10,
      searchValues.state,
      searchValues.city,
      searchValues.major,
      searchValues.name
    );
  }, [page, searchValues]);

  const deleteItem = async (id) => {
    await StudentService.remove(id);
    loadData(
      page,
      10,
      searchValues.state,
      searchValues.city,
      searchValues.major,
      searchValues.name
    );
  };
  // state, String city, String major, String name
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
            <Col span={5}>
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
            </Col>

            <Col span={7}>
              <Form.Item label="name" name="name">
                <Input />
              </Form.Item>
            </Col>
            <Col>
              <SearchOutlined
                onClick={async () => {
                  const values = await form.validateFields();
                  setSearchValues(values);

                  console.log(values);
                }}
              />
            </Col>
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
