const perimeter = (length, width) => {
  if (length === 0 && width === 0) return "Both values can't be 0!";
  return 2 * (length + width);
};

export default perimeter;
