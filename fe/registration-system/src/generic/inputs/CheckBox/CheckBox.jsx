// eslint-disable-next-line react/prop-types
const CheckBox = ({ id, name, label, value, hint, onChange }) => {
  return (
    <div className="flex items-center gap-2">
      <input
        id={id}
        name={name}
        value={value}
        type="checkbox"
        onChange={onChange}
        className="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-600"
      />
      {label && (
        <label
          htmlFor={name}
          className="float-left block text-sm font-medium leading-6 text-gray-900"
        >
          {label}
        </label>
      )}

      {hint && (
        <p className="mt-2 text-sm text-gray-500" id="email-description">
          {hint}
        </p>
      )}
    </div>
  );
};

export default CheckBox;
