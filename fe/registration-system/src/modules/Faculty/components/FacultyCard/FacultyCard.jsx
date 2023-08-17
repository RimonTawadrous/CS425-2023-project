/* eslint-disable react/prop-types */

const FacultyCard = ({ faculty }) => {
  return (
    <div className="w-full bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
      <div className="overflow-hidden rounded-lg bg-white shadow">
        <h2 className="sr-only" id="profile-overview-title">
          Profile Overview
        </h2>
        <div className="bg-white p-6">
          <div className="sm:flex sm:items-center sm:justify-between">
            <div className="sm:flex sm:space-x-5">
              <div className="flex-shrink-0">
                <img
                  className="mx-auto h-20 w-20 rounded-full"
                  src="https://images.unsplash.com/photo-1550525811-e5869dd03032?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                  alt=""
                />
              </div>
              <div className="mt-4 text-center sm:mt-0 sm:pt-1 sm:text-left">
                <p className="text-sm font-medium text-gray-600">Faculty</p>
                <p className="text-xl font-bold text-gray-900 sm:text-2xl">
                  {faculty.baseUser?.firstName} {faculty.baseUser?.lastName}
                </p>
              </div>
            </div>
            <div className="mt-5 flex justify-center sm:mt-0"></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FacultyCard;
