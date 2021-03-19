const getDatabaseUrl = (nodeEnv) => {
  return (
    {
      development: "postgres://postgres:password@localhost:5432/launch_digital_library_development",
      test: "postgres://postgres:password@localhost:5432/launch_digital_library_test",
      e2e: "postgres://postgres:password@localhost:5432/launch_digital_library_e2e",
    }[nodeEnv] || process.env.DATABASE_URL
  );
};

module.exports = getDatabaseUrl;