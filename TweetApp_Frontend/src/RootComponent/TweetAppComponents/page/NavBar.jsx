import React, { useState } from "react";
import {
  ProSidebar,
  Menu,
  MenuItem,
  SidebarHeader,
  SidebarFooter,
  SidebarContent,
} from "react-pro-sidebar";
import {
  FiHome,
  FiLogOut,
  FiArrowLeftCircle,
  FiArrowRightCircle,
} from "react-icons/fi";
import { BiSearch, BiUser } from "react-icons/bi";
import { CgUserList } from "react-icons/cg";
import "react-pro-sidebar/dist/css/styles.css";
import "./header.css";
import { useNavigate, Link } from "react-router-dom";
import { toast } from "react-toastify";
const Header = () => {
  const [menuCollapse, setMenuCollapse] = useState(false);

  const menuIconClick = () => {
    menuCollapse ? setMenuCollapse(false) : setMenuCollapse(true);
  };
  const navigate = useNavigate();
  const logout = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("token");

    toast.success("Logging out!", { autoClose: 3000 });

    navigate("/");
  };
  return (
    <>
      {/* <Toast /> */}
      <div id="header">
        <ProSidebar collapsed={menuCollapse}>
          <SidebarHeader>
            <div className="logotext">
              <p>{menuCollapse ? "Tweet" : "Tweet App"}</p>
            </div>
            <div className="closemenu" onClick={menuIconClick}>
              {/* changing menu collapse icon on click */}
              {menuCollapse ? <FiArrowRightCircle /> : <FiArrowLeftCircle />}
            </div>
          </SidebarHeader>
          <SidebarContent>
            <Menu iconShape="square">
              <MenuItem icon={<FiHome />}>
                <Link to="/tweet">Home</Link>
              </MenuItem>
              <MenuItem icon={<BiUser />}>
                <Link to="/profile">Profile</Link>
              </MenuItem>

              <MenuItem icon={<CgUserList />}>
                <Link to="/users">See Users</Link>
              </MenuItem>

              <MenuItem icon={<BiSearch />}>
                <Link to="/search/users"> Search User</Link>
              </MenuItem>
            </Menu>
          </SidebarContent>
          <SidebarFooter>
            <Menu iconShape="square">
              <MenuItem icon={<FiLogOut />} onClick={logout}>
                Logout
              </MenuItem>
            </Menu>
          </SidebarFooter>
        </ProSidebar>
      </div>
    </>
  );
};

export default Header;
