package com.xtremand.controller;

import com.xtremand.service.VideoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class HomeController
{
  @org.springframework.beans.factory.annotation.Autowired
  private VideoService videoService;
  
  public HomeController() {}
  
  @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String home()
  {
    return "share";
  }
  
  @RequestMapping(value={"/{shortenUrlAlias}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String getVideo(@PathVariable String shortenUrlAlias, Model model) throws Exception
  {
    model.addAttribute("video", videoService.getVideo(shortenUrlAlias));
    return "video";
  }
}
