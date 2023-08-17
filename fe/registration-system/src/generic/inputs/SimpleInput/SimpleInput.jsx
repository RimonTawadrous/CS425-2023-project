/* eslint-disable react/prop-types */
const SimpleInput = ({ id, name, type, placeholder, value, label, hint, className, onChange }) => {
  return (
    <div className={className}>
      {label && (
        <label
          htmlFor={name}
          className="float-left block text-sm font-medium leading-6 text-gray-900"
        >
          {label}
        </label>
      )}

      <div className="mt-2">
        <input
          id={id}
          type={type}
          name={name}
          value={value}
          className="p-2 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          placeholder={placeholder}
          onChange={onChange}
        />
      </div>

      {hint && (
        <p className="mt-2 text-sm text-gray-500" id="email-description">
          {hint}
        </p>
      )}
    </div>
  );
};

export default SimpleInput;
