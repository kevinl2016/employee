import React from 'react';
import App from './App';
import { configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

configure({ adapter: new Adapter() });
// test file
import { shallow } from 'enzyme';

it('renders "Employee List"', () => {
  const wrapper = shallow(<App/>);
  const textHeader = 'Employee List';
  expect(wrapper.contains(textHeader)).toEqual(true);
});
