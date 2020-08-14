module.exports = {
  env: {
    browser: true,
    es2020: true,
    jasmine: true
  },
  extends: [
    'plugin:react/recommended',
    'plugin:testing-library/recommended',
    'plugin:testing-library/react',
    'standard'
  ],
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaFeatures: {
      jsx: true
    },
    ecmaVersion: 11,
    sourceType: 'module'
  },
  plugins: [
    'react',
    '@typescript-eslint',
    'testing-library'
  ],
  rules: {
  },
  settings: {
    react: {
      version: 'detect'
    }
  }
}
